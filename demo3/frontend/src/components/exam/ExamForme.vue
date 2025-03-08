<template>
  <div class="form-container">
    <h2>Créer un Examen</h2>
    <form @submit.prevent="createExam">
      <label>Titre de l'examen</label>
      <input v-model="exam.title" type="text" required />

      <label>ID du Professeur</label>
      <input v-model="exam.teacherId" type="text" required />

      <label>Nom du cours</label>
      <input v-model="exam.course" type="text" required />

      <button type="submit">Ajouter</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const exam = ref({
  title: "",
  teacherId: "",
  course: ""
});

const createExam = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/exams/create", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(exam.value),
    });

    if (!response.ok) throw new Error("Erreur lors de la création de l'examen.");

    alert("Examen créé avec succès !");
    router.push("/");
  } catch (error) {
    console.error(error);
    alert("Une erreur s'est produite lors de la création.");
  }
};
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

form input {
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
