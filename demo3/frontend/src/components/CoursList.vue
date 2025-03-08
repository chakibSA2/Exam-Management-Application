<template>
  <div class="list-container">
    <div class="header">
      <h2>Liste des Cours</h2>
      <router-link to="/create-cours" class="create-button">Créer un Cours</router-link>
    </div>
    
    <table>
      <thead>
        <tr>
          <th>Titre</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="course in courses" :key="course.courseId">
          <td>{{ course.title }}</td>
          <td>
            <button @click="deleteCourse(course.courseId)">Supprimer</button>
          </td>
        </tr>
      </tbody>
    </table>

    <p v-if="courses.length === 0">Aucun cours disponible.</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";

const courses = ref([]);

const fetchCourses = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/courses/all");
    if (!response.ok) throw new Error("Erreur lors de la récupération des cours.");
    courses.value = await response.json();
  } catch (error) {
    console.error(error);
  }
};

const deleteCourse = async (courseId) => {
  if (!confirm("Voulez-vous vraiment supprimer ce cours ?")) return;
  
  try {
    const response = await fetch(`http://localhost:8080/api/courses/delete/${courseId}`, {
      method: "DELETE",
    });

    if (!response.ok) throw new Error("Échec de la suppression du cours.");
    
    courses.value = courses.value.filter(course => course.courseId !== courseId);
  } catch (error) {
    console.error(error);
  }
};

onMounted(fetchCourses);
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
