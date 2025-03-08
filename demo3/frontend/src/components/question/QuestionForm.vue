<template>
    <div class="form-container">
        <h2>Créer une Question</h2>
        <form @submit.prevent="createQuestion">
            <label>Examen</label>
            <select v-model="selectedExamId" required>
                <option value="" disabled>Choisir un examen</option>
                <option v-for="exam in exams" :key="exam.examId" :value="exam.examId">
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

            <button type="submit">Ajouter</button>
        </form>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const exams = ref([]); // Liste des examens récupérés depuis l'API
const selectedExamId = ref(""); // Examen sélectionné

const question = ref({
    title: "",
    category: "",
    difficulty: "",
    option1: "",
    option2: "",
    option3: "",
    option4: "",
    correctAnswer: "",
});

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

// Création d'une question
const createQuestion = async () => {
    if (!selectedExamId.value) {
        alert("Veuillez sélectionner un examen.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/questions/create/${selectedExamId.value}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(question.value),
        });

        if (!response.ok) throw new Error("Erreur lors de la création de la question.");

        alert("Question créée avec succès !");
        router.push(`/questions/${selectedExamId.value}`);
    } catch (error) {
        console.error(error);
        alert("Une erreur s'est produite lors de la création.");
    }
};

onMounted(fetchExams);
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
