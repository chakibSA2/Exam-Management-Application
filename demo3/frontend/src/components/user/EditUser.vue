<template>
    <div class="form-container">
        <h2>Modifier un Utilisateur</h2>
        <form @submit.prevent="updateUser">
            <label>Nom d'utilisateur</label>
            <input v-model="user.username" type="text" required />

            <label>Prénom</label>
            <input v-model="user.firstName" type="text" required />

            <label>Nom</label>
            <input v-model="user.lastName" type="text" required />

            <label>Email</label>
            <input v-model="user.email" type="email" required />

            <label>Rôle</label>
            <select v-model="user.role">
                <option value="ADMIN">Admin</option>
                <option value="TEACHER">Enseignant</option>
                <option value="STUDENT">Étudiant</option>
            </select>

            <label>Statut</label>
            <select v-model="user.active">
                <option :value="true">Actif</option>
                <option :value="false">Inactif</option>
            </select>

            <button type="submit">Modifier</button>
        </form>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";

const router = useRouter();
const route = useRoute();
const userId = route.params.userId;

const user = ref({
    username: "",
    firstName: "",
    lastName: "",
    email: "",
    role: "STUDENT",
    active: true,
});


const fetchUserDetails = async () => {
    try {
        const response = await fetch(`http://localhost:8080/api/users/${userId}`);
        if (!response.ok) throw new Error("Erreur lors de la récupération de l'utilisateur.");
        user.value = await response.json();
    } catch (error) {
        console.error(error);
    }
};


const updateUser = async () => {
    try {
        const response = await fetch("http://localhost:8080/api/users/update", {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(user.value),
        });

        if (!response.ok) throw new Error("Erreur lors de la mise à jour de l'utilisateur.");

        alert("Utilisateur modifié avec succès !");
        router.push("/users");
    } catch (error) {
        console.error(error);
        alert("Une erreur s'est produite lors de la mise à jour.");
    }
};


onMounted(fetchUserDetails);
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

form input,
form select {
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
    background: #ffc107;
    color: black;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background: #e0a800;
}
</style>
