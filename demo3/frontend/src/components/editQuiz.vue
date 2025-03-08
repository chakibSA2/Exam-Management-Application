<template>
    <div class="form-container">
        <h2>Modifier le Quiz</h2>
        <form @submit.prevent="updateQuiz">
            <label>Titre du quiz</label>
            <input v-model="quiz.title" type="text" required />

            <button type="submit">Modifier</button>
        </form>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";

const router = useRouter();
const route = useRoute();
const quizId = route.params.quizId;

const quiz = ref({
    title: "",
});

// Récupération des données du quiz sélectionné
const fetchQuizDetails = async () => {
    try {
        const response = await fetch(`http://localhost:8080/api/quizzes/${quizId}`);
        if (!response.ok) throw new Error("Erreur lors de la récupération du quiz.");
        quiz.value = await response.json();
    } catch (error) {
        console.error(error);
    }
};

// Mise à jour du quiz
const updateQuiz = async () => {
    try {
        const response = await fetch(`http://localhost:8080/api/quizzes/update/${quizId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(quiz.value),
        });

        if (!response.ok) throw new Error("Erreur lors de la modification du quiz.");

        alert("Quiz modifié avec succès !");
        router.push("/quizes");
    } catch (error) {
        console.error(error);
        alert("Une erreur s'est produite lors de la modification.");
    }
};

onMounted(fetchQuizDetails);
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
