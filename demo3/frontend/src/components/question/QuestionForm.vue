<template>
    <div class="form-container">
        <h2>Créer une Question</h2>
        <form @submit.prevent="createQuestion">
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
            <input v-model="question.difficultyLevel" type="text" required />

            <label>Option 1</label>
            <input v-model="question.option1" type="text" required />

            <label>Option 2</label>
            <input v-model="question.option2" type="text" required />

            <label>Option 3</label>
            <input v-model="question.option3" type="text" required />

            <label>Option 4</label>
            <input v-model="question.option4" type="text" required />

            <label>Titre de la question</label>
            <input v-model="question.questionTitle" type="text" required />

            <label>Bonne réponse</label>
            <input v-model="question.rightAnswer" type="text" required />

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
    questionTitle: "",
    category: "",
    difficultyLevel: "",
    option1: "",
    option2: "",
    option3: "",
    option4: "",
    rightAnswer: "",
    exam: {},
    examId: ""
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
    if (!question.value.examId) {
        alert("Veuillez sélectionner un examen.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/questions/create`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(question.value),
        });

        if (!response.ok) throw new Error("Erreur lors de la création de la question.");

        alert("Question créée avec succès !");
        router.push(`/questions`);
    } catch (error) {
        console.error(error);
        alert("Une erreur s'est produite lors de la création.");
    }
};

onMounted(fetchExams);
</script>

<style scoped>
.form-container {
  max-width: 800px;
  margin: 2rem auto;
  padding: 2.5rem;
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.06);
}

h2 {
  color: #1f2937;
  text-align: center;
  margin-bottom: 2.5rem;
  font-size: 1.8rem;
  font-weight: 600;
  position: relative;
  padding-bottom: 1rem;
}

h2::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 80px;
  height: 3px;
  background: #6366f1;
  border-radius: 2px;
}

form {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 1.5rem;
}

label {
  display: block;
  font-weight: 500;
  color: #374151;
  margin-bottom: 0.6rem;
  font-size: 0.95rem;
}

input,
select {
  width: 100%;
  padding: 0.8rem 1rem;
  border: 2px solid #e5e7eb;
  border-radius: 8px;
  font-size: 1rem;
  transition: all 0.2s ease;
  background: #f9fafb;
}

input:focus,
select:focus {
  border-color: #6366f1;
  background: white;
  box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.1);
  outline: none;
}

select {
  appearance: none;
  background-image: url("data:image/svg+xml;charset=UTF-8,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' stroke='%236b7280' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3e%3cpolyline points='6 9 12 15 18 9'%3e%3c/polyline%3e%3c/svg%3e");
  background-repeat: no-repeat;
  background-position: right 1rem center;
  background-size: 1em;
}

button[type="submit"] {
  grid-column: span 2;
  background: #6366f1;
  color: white;
  padding: 1rem 2rem;
  border: none;
  border-radius: 8px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s;
  font-size: 1rem;
  margin-top: 1rem;
}

button[type="submit"]:hover {
  background: #4f46e5;
  transform: translateY(-1px);
  box-shadow: 0 4px 6px rgba(79, 70, 229, 0.1);
}

@media (max-width: 768px) {
  form {
    grid-template-columns: 1fr;
  }

  button[type="submit"] {
    grid-column: span 1;
  }
}

@media (max-width: 640px) {
  .form-container {
    margin: 1rem;
    padding: 1.5rem;
  }

  h2 {
    font-size: 1.5rem;
    margin-bottom: 2rem;
  }

  input,
  select {
    padding: 0.7rem 0.9rem;
  }

  button[type="submit"] {
    width: 100%;
    padding: 1rem;
  }
}
</style>
