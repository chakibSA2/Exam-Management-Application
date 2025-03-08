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
    width: 400px;
    margin: 20px auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background: white;
}

h2 {
    text-align: center;
    margin-bottom: 10px;
}

form label {
    display: block;
    margin-top: 10px;
    font-weight: bold;
}

form input {
    width: 100%;
    padding: 8px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

button {
    width: 100%;
    margin-top: 15px;
    padding: 10px;
    background: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background: #0056b3;
}
</style>
