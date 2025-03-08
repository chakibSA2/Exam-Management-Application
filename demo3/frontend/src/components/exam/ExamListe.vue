<template>
  <div class="list-container">
    <div class="header">
      <h2>Liste des Examens</h2>
      <router-link to="/create-exam" class="create-button">Créer un Examen</router-link>
    </div>

    <table>
      <thead>
        <tr>
          <th>Titre</th>
          <th>Prof</th>
          <th>Cours</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="exam in exams" :key="exam.id">
          <td>{{ exam.title }}</td>
          <td>{{ exam.teacher.name }}</td> 
          <td>{{ exam.course }}</td>
          <td>
            <button @click="deleteExam(exam.id)">Supprimer</button>
          </td>
        </tr>
      </tbody>
    </table>

    <p v-if="exams.length === 0">Aucun examen disponible.</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";

const exams = ref([]);

const fetchExams = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/exams/all");
    if (!response.ok) throw new Error("Erreur lors de la récupération des examens.");
    exams.value = await response.json();
  } catch (error) {
    console.error(error);
  }
};

const deleteExam = async (examId) => {
  if (!confirm("Voulez-vous vraiment supprimer cet examen ?")) return;
  
  try {
    const response = await fetch(`http://localhost:8080/api/exams/delete/${examId}`, {
      method: "DELETE",
    });

    if (!response.ok) throw new Error("Échec de la suppression de l'examen.");
    
    exams.value = exams.value.filter(exam => exam.examId !== examId);
  } catch (error) {
    console.error(error);
  }
};

onMounted(fetchExams);
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

.create-button {
  padding: 8px 12px;
  background: blue;
  color: white;
  text-decoration: none;
  border-radius: 4px;
}

.create-button:hover {
  background: darkblue;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
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
