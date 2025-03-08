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
          <td data-label="ID">{{ user.userId }}</td>
          <td data-label="Nom">{{ user.firstName }} {{ user.lastName }}</td>
          <td data-label="Email">{{ user.email }}</td>
          <td data-label="Rôle">{{ user.role }}</td>
          <td data-label="Actions">
            <div class="button-group">
              <button class="edit-button" @click="editUser(user.userId)">Modifier</button>
              <button class="delete-button" @click="deleteUser(user.userId)">Supprimer</button>
            </div>
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
  max-width: 1000px;
  margin: 2rem auto;
  padding: 25px;
  background-color: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
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
  font-size: 1.7em;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin: 0 auto;
}

th {
  background-color: #3f72af;
  color: white;
  padding: 14px 15px;
  text-align: center; /* Modification ici */
  font-weight: 500;
}

td {
  padding: 12px 15px;
  border-bottom: 1px solid #e0e0e0;
  background-color: white;
  text-align: center; /* Ajout pour aligner tout le contenu */
}

td:first-child,
td:nth-child(4) {
  text-align: center; /* Renforcement de l'alignement */
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

@media (max-width: 768px) {
  .button-group {
    justify-content: flex-end;
  }
}

@media (max-width: 480px) {
  .button-group {
    flex-direction: column;
    gap: 6px;
  }

  .edit-button,
  .delete-button {
    width: 100%;
  }
}

button {
  background-color: #e74c3c;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s;
}

button:hover {
  background-color: #c0392b;
  transform: translateY(-1px);
}

@media (max-width: 768px) {
  .list-container {
    padding: 15px;
  }

  th { display: none; }

  td {
    display: block;
    text-align: right;
    padding-left: 45%;
    position: relative;
  }

  td::before {
    content: attr(data-label);
    position: absolute;
    left: 0;
    width: 40%;
    padding-left: 15px;
    font-weight: 600;
    text-align: left;
  }

  td:first-child {
    text-align: right;
    padding-left: 45%;
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
}
</style>