<template>
    <div class="list-container">
        <div class="header">
            <h2>Liste des Quiz</h2>
            <router-link to="/create-quiz" class="create-button">Créer un Quiz</router-link>
        </div>

        <table>
            <thead>
                <tr>
                    <th>Titre</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="quiz in quizStore.quizzes" :key="quiz.quizId">
                    <td>{{ quiz.title }}</td>
                    <td>
                        <button @click="quizStore.deleteQuiz(quiz.quizId)">Supprimer</button>
                        <router-link :to="`/edit-quiz/${quiz.quizId}`" class="edit-button">Modifier</router-link>

                    </td>
                </tr>
            </tbody>
        </table>

        <p v-if="quizStore.quizzes.length === 0">Aucun quiz disponible.</p>
    </div>
</template>

<script setup>
import { onMounted } from "vue";
import { useQuizStore } from "@/store/quizStore";

const quizStore = useQuizStore();

onMounted(quizStore.fetchQuizzes);
</script>


<style scoped>
.list-container {
    width: 600px;
    margin: 20px auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 5px;
    background: white;
}

.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}

h2 {
    margin: 0;
}

.create-button,
.edit-button {
    padding: 8px 12px;
    background: blue;
    color: white;
    text-decoration: none;
    border-radius: 4px;
    margin-left: 10px;
}

.create-button:hover,
.edit-button:hover {
    background: darkblue;
}

table {
    width: 100%;
    border-collapse: collapse;
}

th,
td {
    padding: 10px;
    border-bottom: 1px solid #ddd;
    text-align: left;
}

th {
    background: #f4f4f4;
}

button {
    background: red;
    color: white;
    padding: 5px 10px;
    border: none;
    cursor: pointer;
    border-radius: 3px;
}

button:hover {
    background: darkred;
}
</style>
