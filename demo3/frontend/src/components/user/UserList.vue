<template>
  <div class="list-container">
    <div class="header">
      <h2>Liste des Utilisateurs</h2>
      <router-link to="/create-user" class="create-button">Créer un Utilisateur</router-link>
    </div>
    
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Nom</th>
          <th>Email</th>
          <th>Rôle</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.userId }}</td>
          <td>{{ user.firstName }} {{ user.lastName }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.role }}</td>
          <td>
            <button @click="deleteUser(user.id)">Supprimer</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";

const users = ref([]);

const fetchUsers = async () => {
  const response = await fetch("http://localhost:8080/api/users/all");
  users.value = await response.json();
};

const deleteUser = async (userId) => {
  await fetch(`http://localhost:8080/api/users/${userId}`, { method: "DELETE" });
  users.value = users.value.filter(user => user.userId !== userId);
};

onMounted(fetchUsers);
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
