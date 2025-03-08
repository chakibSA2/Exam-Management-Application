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
        <tr v-for="course in courses" :key="course.id">
          <td data-label="Titre">{{ course.title }}</td>
          <td data-label="Actions">
            <div class="button-group">
              <router-link :to="`/edit-course/${course.id}`" class="edit-button">Modifier</router-link>
              <button class="delete-button" @click="deleteCourse(course.id)">Supprimer</button>
            </div>
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
  max-width: 800px;
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
  text-align: center;
  flex-grow: 1;
}

table {
  width: auto;
  border-collapse: collapse;
  margin: 0 auto;
  min-width: 600px;
}

th {
  background-color: #3f72af;
  color: white;
  padding: 14px 15px;
  text-align: center;
  font-weight: 500;
}

td {
  padding: 12px 15px;
  border-bottom: 1px solid #ddd;
  background-color: white;
  text-align: center;
}

tr:hover td {
  background-color: #f5f9ff;
}

.create-button {
  background-color: #2d8a4d;
  color: white;
  padding: 10px 25px;
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.create-button:hover {
  background-color: #24773f;
}

.button-group {
  display: flex;
  gap: 8px;
  justify-content: center;
}

.edit-button {
  background-color: #3f72af;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s;
}

.delete-button {
  background-color: #e74c3c;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s;
}

.edit-button:hover {
  background-color: #325f8f;
  transform: translateY(-1px);
}

.delete-button:hover {
  background-color: #c0392b;
  transform: translateY(-1px);
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

  .button-group {
    justify-content: flex-end;
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

  td {
    padding-left: 40%;
  }

  td::before {
    width: 35%;
  }

  .button-group {
    flex-direction: column;
    gap: 6px;
  }

  .edit-button,
  .delete-button {
    width: 100%;
  }
}
</style>
