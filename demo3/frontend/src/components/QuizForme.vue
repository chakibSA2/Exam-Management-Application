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
