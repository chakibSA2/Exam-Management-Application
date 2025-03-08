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
                <tr v-for="quiz in quizStore.quizzes" :key="quiz.id">
                    <td>{{ quiz.title }}</td>
                    <td>
                      <div class=button-group>
                        <router-link :to="`/edit-quiz/${quiz.id}`" class="edit-button">Modifier</router-link>
                        <button class="delete-button" @click="quizStore.deleteQuiz(quiz.id)">Supprimer</button>
                      </div>
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
  max-width: 800px;
  margin: 2rem auto;
  padding: 1.5rem;
  background: #f8f9fa;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
  padding: 0 0.5rem;
}

h2 {
  color: #1a365d;
  font-size: 1.8rem;
  font-weight: 600;
  margin: 0;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin: 1.5rem 0;
}

th {
  background-color: #3498db;
  color: white;
  padding: 1rem;
  text-align: center;
  font-weight: 500;
  letter-spacing: 0.5px;
}

td {
  padding: 1rem;
  border-bottom: 1px solid #e2e8f0;
  background-color: white;
}

tr:last-child td {
  border-bottom: none;
}

tr:hover td {
  background-color: #f8fafc;
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

/* Responsive Design */
@media (max-width: 768px) {
  /* ... (styles existants) ... */

  .button-group {
    flex-direction: column;
  }

  .edit-button,
  .delete-button {
    width: 100%;
    justify-content: center;
  }
}

p {
  text-align: center;
  color: #64748b;
  padding: 2rem 0;
  font-style: italic;
}

/* Responsive Design */
@media (max-width: 768px) {
  .list-container {
    margin: 1rem;
    padding: 1rem;
    border-radius: 8px;
  }

  th { display: none; }

  td {
    display: block;
    padding: 1rem 0.5rem;
    text-align: right;
    position: relative;
    padding-left: 45%;
  }

  td::before {
    content: attr(data-label);
    position: absolute;
    left: 0.5rem;
    width: 40%;
    text-align: left;
    font-weight: 500;
    color: #1e293b;
  }

  .edit-button, button {
    width: 100%;
    text-align: center;
    margin: 0.25rem 0;
    padding: 8px 12px;
    border-radius: 4px;
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
    padding-left: 50%;
  }
}
</style>