<template>
  <div class="form-container">
    <h2>Créer un Examen</h2>
    <form @submit.prevent="createExam">
      <label>Titre de l'examen</label>
      <input v-model="exam.title" type="text" required />

      <label>Date de l'examen</label>
      <input v-model="exam.date" type="datetime-local" required />

      <label>Professeur</label>
      <select v-model="exam.teacherId" required>
        <option value="" disabled>Sélectionner un professeur</option>
        <option v-for="teacher in teachers" :key="teacher.userId" :value="teacher.userId">
          {{ teacher.firstName }} {{ teacher.lastName }}
        </option>
      </select>

      <label>Cours</label>
      <select v-model="exam.courseId" required>
        <option value="" disabled>Sélectionner un cours</option>
        <option v-for="course in courses" :key="course.id" :value="course.id">
          {{ course.title }}
        </option>
      </select>

      <button type="submit">Ajouter</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const exam = ref({
  title: "",
  date: "",
  teacherId: "",
  courseId: "",
});

const teachers = ref([]);
const courses = ref([]);

const fetchTeachers = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/users/teachers");
    if (!response.ok) throw new Error("Erreur lors de la récupération des enseignants.");
    teachers.value = await response.json();
  } catch (error) {
    console.error(error);
  }
};

const fetchCourses = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/courses/all");
    if (!response.ok) throw new Error("Erreur lors de la récupération des cours.");
    courses.value = await response.json();
  } catch (error) {
    console.error(error);
  }
};

const createExam = async () => {
  if (!exam.value.date) {
    alert("Veuillez sélectionner une date.");
    return;
  }

  try {
    exam.value.date = new Date(exam.value.date).toISOString(); // Conversion au format ISO
    const response = await fetch("http://localhost:8080/api/exams/create", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(exam.value),
    });

    if (!response.ok) throw new Error("Erreur lors de la création de l'examen.");

    alert("Examen créé avec succès !");
    router.push("/exams");
  } catch (error) {
    console.error(error);
    alert("Une erreur s'est produite lors de la création.");
  }
};

onMounted(() => {
  fetchTeachers();
  fetchCourses();
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
