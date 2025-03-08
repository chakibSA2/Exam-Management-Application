<template>
    <div class="form-container">
        <h2>Modifier une Question</h2>
        <form @submit.prevent="updateQuestion">
            <label>Examen</label>
            <select v-model="question.examId" required>
                <option value="" disabled>Choisir un examen</option>
                <option v-for="exam in exams" :key="exam.id" :value="exam.id">
                    {{ exam.title }}
                </option>
            </select>

            <label>Catégorie</label>
            <input v-model="question.category" type="text" required />

            <label>Niveau de difficulté</label>
            <input v-model="question.difficulty" type="text" required />

            <label>Option 1</label>
            <input v-model="question.option1" type="text" required />

            <label>Option 2</label>
            <input v-model="question.option2" type="text" required />

            <label>Option 3</label>
            <input v-model="question.option3" type="text" required />

            <label>Option 4</label>
            <input v-model="question.option4" type="text" required />

            <label>Titre de la question</label>
            <input v-model="question.title" type="text" required />

            <label>Bonne réponse</label>
            <input v-model="question.correctAnswer" type="text" required />

            <button type="submit">Modifier</button>
        </form>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";

const router = useRouter();
const route = useRoute();
const questionId = route.params.questionId; // Récupération de l'ID de la question

const question = ref({
    title: "",
    category: "",
    difficulty: "",
    option1: "",
    option2: "",
    option3: "",
    option4: "",
    correctAnswer: "",
    examId: "",
});

const exams = ref([]);

// Récupération des examens depuis l'API
const fetchExams = async () => {
    try {
        const response = await fetch("http://localhost:8080/api/exams/all");
        if (!response.ok) throw new Error("Erreur lors de la récupération des examens.");
        exams.value = await response.json();
    } catch (error) {
        console.error(error);
    }
};

// Récupération des données de la question existante
const fetchQuestionDetails = async () => {
    try {
        const response = await fetch(`http://localhost:8080/api/questions/${questionId}`);
        if (!response.ok) throw new Error("Erreur lors de la récupération de la question.");
        question.value = await response.json();
    } catch (error) {
        console.error(error);
    }
};

// Mise à jour de la question
const updateQuestion = async () => {
    try {
        const response = await fetch("http://localhost:8080/api/questions/update", {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                id: questionId,
                title: question.value.title,
                category: question.value.category,
                difficulty: question.value.difficulty,
                option1: question.value.option1,
                option2: question.value.option2,
                option3: question.value.option3,
                option4: question.value.option4,
                correctAnswer: question.value.correctAnswer,
                examId: question.value.examId,
            }),
        });

        if (!response.ok) throw new Error("Erreur lors de la mise à jour de la question.");

        alert("Question modifiée avec succès !");
        router.push("/questions"); // Redirection vers la liste des questions
    } catch (error) {
        console.error(error);
        alert("Une erreur s'est produite lors de la mise à jour.");
    }
};

// Chargement des données à l'affichage du composant
onMounted(() => {
    fetchExams();
    fetchQuestionDetails();
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

form input,
form select {
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
    background: #ffc107;
    color: black;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background: #e0a800;
}
</style>
