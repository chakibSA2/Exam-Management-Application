<template>
    <div class="form-container">
        <h2>Créer un Quiz</h2>
        <form @submit.prevent="createQuiz">
            <label>Titre du quiz</label>
            <input v-model="quiz.title" type="text" required />

            <button type="submit">Ajouter</button>
        </form>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const quiz = ref({
    title: "",
});

const createQuiz = async () => {
    try {
        const response = await fetch("http://localhost:8080/api/quizzes/create", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(quiz.value),
        });

        if (!response.ok) throw new Error("Erreur lors de la création du quiz.");

        alert("Quiz créé avec succès !");
        router.push("/quizes");
    } catch (error) {
        console.error(error);
        alert("Une erreur s'est produite lors de la création.");
    }
};
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
