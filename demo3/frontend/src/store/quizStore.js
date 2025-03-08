import { defineStore } from "pinia";

export const useQuizStore = defineStore("quizStore", {
    state: () => ({
        quizzes: [],
        selectedQuiz: {},
    }),

    actions: {
        async fetchQuizzes() {
            try {
                const response = await fetch("http://localhost:8080/api/quizzes/all");
                if (!response.ok) throw new Error("Erreur lors de la récupération des quiz.");
                this.quizzes = await response.json();
            } catch (error) {
                console.error(error);
            }
        },

        async fetchQuizById(quizId) {
            try {
                const response = await fetch(`http://localhost:8080/api/quizzes/${quizId}`);
                if (!response.ok) throw new Error("Quiz non trouvé.");
                this.selectedQuiz = await response.json();
            } catch (error) {
                console.error(error);
            }
        },

        async updateQuiz(quizId, updatedQuiz) {
            try {
                const response = await fetch(`http://localhost:8080/api/quizzes/update/${quizId}`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(updatedQuiz),
                });

                if (!response.ok) throw new Error("Erreur lors de la mise à jour du quiz.");
                alert("Quiz modifié avec succès !");
                this.fetchQuizzes();
            } catch (error) {
                console.error(error);
            }
        },

        async deleteQuiz(quizId) {
            if (!confirm("Voulez-vous vraiment supprimer ce quiz ?")) return;

            try {
                const response = await fetch(`http://localhost:8080/api/quizzes/${quizId}`, {
                    method: "DELETE",
                });

                if (!response.ok) throw new Error("Échec de la suppression du quiz.");
                this.quizzes = this.quizzes.filter(quiz => quiz.quizId !== quizId);
            } catch (error) {
                console.error(error);
            }
        },
    },
});
