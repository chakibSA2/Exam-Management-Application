<template>
    <div class="form-container">
        <h2>Modifier le Cours</h2>
        <form @submit.prevent="updateCourse">
            <label>Titre du cours</label>
            <input v-model="course.title" type="text" required />

            <button type="submit">Modifier</button>
        </form>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";

const router = useRouter();
const route = useRoute();
const courseId = route.params.courseId; // Récupération de l'ID du cours dans l'URL

const course = ref({
    title: "",
});

// Récupérer les informations du cours sélectionné
const fetchCourseDetails = async () => {
    try {
        const response = await fetch(`http://localhost:8080/api/courses/${courseId}`);
        if (!response.ok) throw new Error("Erreur lors de la récupération du cours.");
        course.value = await response.json();
    } catch (error) {
        console.error(error);
    }
};

// Mettre à jour le cours
const updateCourse = async () => {
    try {
        const response = await fetch(`http://localhost:8080/api/courses/update`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ id: courseId, title: course.value.title }), // Envoi de l'ID et du titre modifié
        });

        if (!response.ok) throw new Error("Erreur lors de la mise à jour du cours.");

        alert("Cours modifié avec succès !");
        router.push("/cours"); // Redirection vers la liste des cours
    } catch (error) {
        console.error(error);
        alert("Une erreur s'est produite lors de la mise à jour.");
    }
};

onMounted(fetchCourseDetails);
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
    background: #28a745;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background: #218838;
}
</style>
