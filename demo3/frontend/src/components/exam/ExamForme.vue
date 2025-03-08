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
  max-width: 600px;
  margin: 2rem auto;
  padding: 2rem;
  background: white;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
}

h2 {
  color: #1a365d;
  text-align: center;
  margin-bottom: 2rem;
  font-size: 1.8rem;
  font-weight: 600;
  padding-bottom: 1rem;
  border-bottom: 2px solid #e2e8f0;
}

form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

label {
  font-weight: 500;
  color: #2d3748;
  margin-bottom: 0.5rem;
  display: block;
  font-size: 0.95rem;
}

input,
select {
  width: 100%;
  padding: 0.75rem 1rem;
  border: 2px solid #e2e8f0;
  border-radius: 6px;
  font-size: 1rem;
  transition: all 0.2s ease;
  background: white;
}

input:focus,
select:focus {
  border-color: #4f46e5;
  outline: none;
  box-shadow: 0 0 0 3px rgba(79, 70, 229, 0.1);
}

input[type="datetime-local"] {
  font-family: inherit;
}

select {
  appearance: none;
  background-image: url("data:image/svg+xml;charset=UTF-8,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3e%3cpolyline points='6 9 12 15 18 9'%3e%3c/polyline%3e%3c/svg%3e");
  background-repeat: no-repeat;
  background-position: right 1rem center;
  background-size: 1em;
}

button[type="submit"] {
  background-color: #4f46e5;
  color: white;
  padding: 1rem 2rem;
  border: none;
  border-radius: 6px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s;
  margin-top: 1rem;
  font-size: 1rem;
}

button[type="submit"]:hover {
  background-color: #4338ca;
  transform: translateY(-1px);
}

@media (max-width: 640px) {
  .form-container {
    margin: 1rem;
    padding: 1.5rem;
  }

  h2 {
    font-size: 1.5rem;
  }

  input,
  select {
    padding: 0.65rem 0.9rem;
    font-size: 0.9rem;
  }

  button[type="submit"] {
    width: 100%;
    padding: 0.9rem;
  }
}
</style>