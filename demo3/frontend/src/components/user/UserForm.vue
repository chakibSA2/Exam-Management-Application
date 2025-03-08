<template>
  <div class="form-container">
    <h2>Créer un Utilisateur</h2>
    <form @submit.prevent="createUser">
      <label>Nom d'utilisateur</label>
      <input v-model="user.username" type="text" required />

      <label>Prénom</label>
      <input v-model="user.firstName" type="text" required />

      <label>Nom</label>
      <input v-model="user.lastName" type="text" required />

      <label>Email</label>
      <input v-model="user.email" type="email" required />

      <label>Mot de passe</label>
      <input v-model="user.password" type="password" required />

      <label>Rôle</label>
      <select v-model="user.role">
        <option value="ADMIN">Admin</option>
        <option value="TEACHER">Enseignant</option>
        <option value="STUDENT">Étudiant</option>
      </select>

      <button type="submit">Ajouter</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";

const user = ref({
  username: "",
  firstName: "",
  lastName: "",
  email: "",
  password: "",
  role: "STUDENT",
});

const emit = defineEmits(["userCreated"]);

const createUser = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/users/create", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(user.value),
    });

    if (!response.ok) throw new Error("Erreur lors de la création");

    const newUser = await response.json();
    emit("userCreated", newUser);

    user.value = { username: "", firstName: "", lastName: "", email: "", password: "", role: "STUDENT" };
  } catch (error) {
    console.error(error);
  }
};
</script>

<style scoped>
.form-container {
  max-width: 500px;
  margin: 2rem auto;
  padding: 25px;
  background-color: #f8f9fa;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
}

h2 {
  color: #2c3e50;
  text-align: center;
  margin-bottom: 1.5rem;
  font-size: 1.8em;
}

form {
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
}

label {
  font-weight: 600;
  color: #4a5568;
  margin-bottom: 0.5rem;
  display: block;
}

input, select {
  width: 100%;
  padding: 10px 15px;
  border: 2px solid #e2e8f0;
  border-radius: 6px;
  font-size: 1em;
  transition: all 0.3s ease;
}

input:focus, select:focus {
  border-color: #4a90e2;
  outline: none;
  box-shadow: 0 0 0 3px rgba(74, 144, 226, 0.2);
}

button[type="submit"] {
  background-color: #4f46e5;
  color: white;
  padding: 12px 25px;
  border: none;
  border-radius: 6px;
  font-size: 1.1em;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-top: 1rem;
}

button[type="submit"]:hover {
  background-color: #24773f;
}

@media (max-width: 600px) {
  .form-container {
    margin: 1rem;
    padding: 20px;
  }

  input, select {
    padding: 8px 12px;
  }

  button[type="submit"] {
    width: 100%;
    padding: 12px;
  }
}

@media (max-width: 400px) {
  h2 {
    font-size: 1.5em;
  }

  label {
    font-size: 0.9em;
  }
}
</style>
