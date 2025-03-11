<template>
    <div class="form-container">
        <h2>Gestion des questions du quiz</h2>
        <form @submit.prevent="handleSubmit">
            <div v-for="(selection, index) in selections" :key="index" class="selection-group">
                <select v-model="selection.id" required>
                    <option value="" disabled>Sélectionner une question</option>
                    <option v-for="question in availableQuestions" :key="question.id" :value="question.id">
                        {{ question.questionTitle }}
                    </option>
                </select>
                <div class="button-group">
                    <button v-if="selections.length > 1" @click="removeSelection(index)" type="button"
                        class="remove-btn">
                        -
                    </button>
                    <button @click="addSelection" type="button" class="add-btn">
                        +
                    </button>
                </div>
            </div>

            <div class="action-buttons">
                <button type="button" @click="associateQuestions" class="associate-btn">Ajouter les
                    associations</button>
                <button type="button" @click="removeQuestions" class="remove-associations-btn">Enlever les
                    associations</button>
            </div>
        </form>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const quizId = route.params.quizId;

const allQuestions = ref([]);
const selections = ref([{ id: null }]);


onMounted(async () => {
    const response = await fetch('http://localhost:8080/api/questions/all');
    allQuestions.value = await response.json();
});


const availableQuestions = computed(() => {
    const selectedIds = selections.value.map(s => s.id);
    return allQuestions.value.filter(q => !selectedIds.includes(q.id));
});

const addSelection = () => {
    selections.value.push({ id: null });
};

const removeSelection = (index) => {
    selections.value.splice(index, 1);
};

const associateQuestions = async () => {
    const questionIds = selections.value
        .map(s => s.id)
        .filter(id => id !== null);

    try {
        await fetch(`http://localhost:8080/api/quizzes/${quizId}/associate-questions`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(questionIds)
        });
        alert('Associations ajoutées avec succès !');
        router.push('/quizes');
    } catch (error) {
        console.error(error);
        alert("Erreur lors de l'ajout");
    }
};

const removeQuestions = async () => {
    const questionIds = selections.value
        .map(s => s.id)
        .filter(id => id !== null);

    try {
        await fetch(`http://localhost:8080/api/quizzes/${quizId}/remove-questions`, {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(questionIds)
        });
        alert('Associations supprimées avec succès !');
        router.push('/quizes');
    } catch (error) {
        console.error(error);
        alert("Erreur lors de la suppression");
    }
};
</script>

<style scoped>
.selection-group {
    display: flex;
    gap: 0.5rem;
    margin-bottom: 1rem;
    align-items: center;
}

select {
    flex: 1;
    padding: 0.5rem;
    border: 2px solid #e2e8f0;
    border-radius: 4px;
}

.button-group {
    display: flex;
    gap: 0.25rem;
}

.add-btn,
.remove-btn {
    width: 30px;
    height: 30px;
    border: none;
    border-radius: 50%;
    cursor: pointer;
    font-weight: bold;
    transition: all 0.2s;
}

.add-btn {
    background-color: #4CAF50;
    color: white;
}

.remove-btn {
    background-color: #f44336;
    color: white;
}

.add-btn:hover {
    background-color: #45a049;
}

.remove-btn:hover {
    background-color: #da190b;
}

.action-buttons {
    margin-top: 2rem;
    display: flex;
    gap: 1rem;
    justify-content: center;
}

.associate-btn {
    background-color: #2196F3;
    color: white;
    padding: 0.5rem 1rem;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.remove-associations-btn {
    background-color: #ff9800;
    color: white;
    padding: 0.5rem 1rem;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.associate-btn:hover {
    background-color: #1976D2;
}

.remove-associations-btn:hover {
    background-color: #f57c00;
}
</style>