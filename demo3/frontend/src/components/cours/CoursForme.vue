<template>
  <div class="form-container">
    <h2>Créer un Cours</h2>
    <form @submit.prevent="createCourse">
      <label>Titre du cours</label>
      <input v-model="course.title" type="text" required />

      <button type="submit">Ajouter</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const course = ref({
  title: "",
});

const createCourse = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/courses/create", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(course.value),
    });

    if (!response.ok) throw new Error("Erreur lors de la création du cours.");

    alert("Cours créé avec succès !");
    router.push("/exams");
  } catch (error) {
    console.error(error);
    alert("Une erreur s'est produite lors de la création.");
  }
};
</script>

<style scoped>
.form-container {
  max-width: 500px;
  margin: 2rem auto;
  padding: 2rem;
  background: white;
  border-radius: 10px;
  box-shadow: 0 2px 15px rgba(0, 0, 0, 0.08);
}

h2 {
  color: #2d3748;
  text-align: center;
  margin-bottom: 2rem;
  font-size: 1.8rem;
  font-weight: 600;
  padding-bottom: 1rem;
  border-bottom: 2px solid #f0f4f8;
}

form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

label {
  font-weight: 500;
  color: #4a5568;
  margin-bottom: 0.5rem;
  display: block;
  font-size: 0.95rem;
}

input {
  width: 100%;
  padding: 0.8rem 1rem;
  border: 2px solid #e2e8f0;
  border-radius: 6px;
  font-size: 1rem;
  transition: all 0.2s ease;
}

input:focus {
  border-color: #667eea;
  outline: none;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

button[type="submit"] {
  background-color: #667eea;
  color: white;
  padding: 0.9rem 1.5rem;
  border: none;
  border-radius: 6px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s;
  font-size: 1rem;
  margin-top: 0.5rem;
}

button[type="submit"]:hover {
  background-color: #5a67d8;
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

  input {
    padding: 0.7rem 0.9rem;
    font-size: 0.95rem;
  }

  button[type="submit"] {
    width: 100%;
    padding: 0.9rem;
  }
}
</style>