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
          <th>Professeur</th>
          <th>Cours</th>
          <th>Date</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="exam in exams" :key="exam.id">
          <td data-label="Titre">{{ exam.title }}</td>
          <td data-label="Professeur">{{ exam.teacher.firstName }} {{ exam.teacher.lastName }}</td>
          <td data-label="Cours">{{ exam.course.title }}</td>
          <td data-label="Date">{{ formatDate(exam.date) }}</td>
          <td data-label="Actions">
            <div class="button-group">
              <router-link :to="`/edit-exam/${exam.id}`" class="edit-button">Modifier</router-link>
              <button class="delete-button" @click="deleteExam(exam.id)">Supprimer</button>
            </div>
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

// Ajoutez cette fonction de formatage
const formatDate = (dateString) => {
  const date = new Date(dateString);
  return date.toLocaleDateString('fr-FR', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
  }).replace(',', '');
};

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

    exams.value = exams.value.filter(exam => exam.id !== examId);
  } catch (error) {
    console.error(error);
  }
};

onMounted(fetchExams);
</script>

<style scoped>
.list-container {
  max-width: 1200px;
  margin: 2rem auto;
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  width: fit-content;
  min-width: 60%;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
  padding: 0 10px;
}

h2 {
  color: #2c3e50;
  margin: 0;
}

table {
  width: auto;
  border-collapse: collapse;
  margin: 0 auto;
  min-width: 600px;
}

th {
  background-color: #3498db;
  color: white;
  padding: 12px 15px;
  text-align: center;
  font-weight: 600;
}

td {
  padding: 12px 15px;
  border-bottom: 1px solid #ddd;
  text-align: left;
  background-color: white;
}

tr:last-child td {
  border-bottom: none;
}

tr:hover td {
  background-color: #f5f5f5;
}

.create-button {
  background-color: #28a745;
  color: white;
  padding: 0.75rem 1.5rem;
  border-radius: 6px;
  text-decoration: none;
  font-weight: 500;
  transition: transform 0.2s, background-color 0.2s;
}

.create-button:hover {
  background-color: #218838;
  transform: translateY(-1px);
}

.button-group {
  display: flex;
  gap: 0.5rem;
  justify-content: center;
}

.edit-button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.2s;
  font-size: 0.9rem;
}

.delete-button {
  background-color: #dc3545;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.2s;
  font-size: 0.9rem;
}

.edit-button:hover,
.delete-button:hover {
  opacity: 0.9;
  transform: translateY(-1px);
}

button {
  background-color: #dc3545;
  color: white;
  border: none;
  padding: 8px 12px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #c82333;
}

p {
  text-align: center;
  color: #6c757d;
  padding: 20px;
}

@media (max-width: 768px) {
  .list-container {
    width: 95%;
    padding: 10px;
  }

  table {
    min-width: unset;
    width: 100%;
  }

  th {
    display: none;
  }

  td {
    display: block;
    text-align: right;
    padding-left: 50%;
    position: relative;
    white-space: normal;
  }

  td::before {
    content: attr(data-label);
    position: absolute;
    left: 0;
    width: 45%;
    padding-left: 15px;
    font-weight: bold;
    text-align: left;
  }
}

@media (max-width: 480px) {
  .header {
    flex-direction: column;
    gap: 1rem;
  }

  .create-button {
    width: 100%;
    text-align: center;
  }
}
</style>
