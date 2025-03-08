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

form input, form select {
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
