<template>
  <div class="home-container">
    <h1>Bienvenue sur la plateforme de gestion des examens</h1>
    <p>Sélectionnez une option pour commencer :</p>

    <div class="cards-container">
      <div class="card" @click="$router.push('/exams')">
        <h3>📄 Examens</h3>
        <p>Gérer et consulter les examens.</p>
      </div>

      <div class="card" @click="$router.push('/users')" v-if="!isStudent">
        <h3>👤 Utilisateurs</h3>
        <p>Gérer les étudiants et enseignants.</p>
      </div>

      <div class=" card" @click="$router.push('/cours')" v-if="!isStudent">
        <h3>📚 Cours</h3>
        <p>Liste des cours disponibles.</p>
      </div>

      <div class="card" @click="$router.push('/questions')" v-if="isTeacher">
        <h3>❓ Questions</h3>
        <p>Ajouter et modifier les questions d’examen.</p>
      </div>

      <div class="card" @click="$router.push('/quizes')" v-if="isStudent || isTeacher">
        <h3>📝 Quiz</h3>
        <p>Créer et gérer des quiz.</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from "vue";
import { useRouter } from "vue-router";
import { authStore } from "@/store/authStore";

const isTeacher = computed(() => authStore.hasRole('TEACHER').value);
const isStudent = computed(() => authStore.hasRole('STUDENT').value);

const router = useRouter();
</script>

<style scoped>
/* Styles */
.home-container {
  text-align: center;
  padding: 40px 20px;
}

h1 {
  color: #007bff;
  font-size: 28px;
}

p {
  font-size: 16px;
  color: #555;
}

.cards-container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
  padding: 30px;
  max-width: 900px;
  margin: auto;
}

.card {
  cursor: pointer;
  background: white;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.2s, box-shadow 0.2s;
}

.card:hover {
  transform: scale(1.05);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}
</style>
