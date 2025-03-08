<template>
    <div class="form-container">
        <h2>Modifier le Quiz</h2>
        <form @submit.prevent="quizStore.updateQuiz(quizStore.selectedQuiz.id, quizStore.selectedQuiz)">
            <label>Titre du quiz</label>
            <input v-if="quizStore.selectedQuiz" v-model="quizStore.selectedQuiz.title" type="text" required />
            <p v-else>Chargement du quiz...</p>

            <button type="submit">Modifier</button>
        </form>
    </div>
</template>

<script setup>
import { onMounted } from "vue";
import { useRoute } from "vue-router";
import { useQuizStore } from "@/store/quizStore";

const quizStore = useQuizStore();
const route = useRoute();
const quizId = route.params.quizId;
console.log("Quiz ID reçu dans EditQuiz.vue :", quizId);

onMounted(() => {
    if (!quizId) {
        console.error("Erreur : quizId est undefined !");
        return;
    }
    quizStore.fetchQuizById(quizId);
});
</script>


<style scoped>
.form-container {
  max-width: 500px;
  margin: 3rem auto;
  padding: 2.5rem;
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.05);
}

h2 {
  color: #2d3748;
  text-align: center;
  margin-bottom: 2.5rem;
  font-size: 1.8rem;
  font-weight: 600;
  letter-spacing: -0.025em;
  position: relative;
  padding-bottom: 1rem;
}

h2::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 60px;
  height: 3px;
  background: #4f46e5;
  border-radius: 2px;
}

form {
  display: flex;
  flex-direction: column;
  gap: 1.8rem;
}

label {
  font-weight: 500;
  color: #4a5568;
  font-size: 0.95rem;
  display: block;
  margin-bottom: 0.6rem;
}

input {
  width: 100%;
  padding: 0.9rem 1.2rem;
  border: 2px solid #e2e8f0;
  border-radius: 8px;
  font-size: 1rem;
  transition: all 0.25s cubic-bezier(0.4, 0, 0.2, 1);
  background: #f8fafc;
}

input:focus {
  border-color: #6366f1;
  background: white;
  box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.1);
  outline: none;
}

button[type="submit"] {
  background: #6366f1;
  color: white;
  padding: 1rem 2rem;
  border: none;
  border-radius: 8px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.25s ease;
  font-size: 1rem;
  margin-top: 0.5rem;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
}

button[type="submit"]:hover {
  background: #4f46e5;
  transform: translateY(-1px);
  box-shadow: 0 4px 6px rgba(79, 70, 229, 0.1);
}

@media (max-width: 640px) {
  .form-container {
    margin: 1.5rem;
    padding: 2rem 1.5rem;
    border-radius: 10px;
  }

  h2 {
    font-size: 1.6rem;
    margin-bottom: 2rem;
  }

  input {
    padding: 0.8rem 1rem;
  }

  button[type="submit"] {
    width: 100%;
    padding: 1rem;
  }
}
</style>
