import { createRouter, createWebHistory } from "vue-router";

// Importation des vues
import UsersView from "@/views/UsersView.vue";
import UserForm from "@/components/user/UserForm.vue";
import EditUser from "@/components/user/EditUser.vue";

import ExamListe from "@/components/exam/ExamListe.vue";
import ExamForme from "@/components/exam/ExamForme.vue";
import EditExam from "@/components/exam/EditExam.vue";

import CoursView from "@/views/CoursView.vue";
import CoursForme from "@/components/cours/CoursForme.vue";
import EditCours from "@/components/cours/EditCours.vue";

import QuestionView from "@/views/QuestionView.vue";
import QuestionForm from "@/components/question/QuestionForm.vue";
import EditQuestion from "@/components/question/EditQuestion.vue";

import QuizView from "@/views/QuizView.vue";
import QuizForme from "@/components/quiz/QuizForme.vue";
import EditQuiz from "@/components/quiz/EditQuiz.vue";
import QuizAssociation from "@/components/quiz/QuizAssociation.vue";

import Login from "@/views/Login.vue";
import HomeView from "@/views/HomeView.vue";

const routes = [
    { path: "/", component: Login },

    { path: "/home", component: HomeView, meta: { requiresAuth: true } },


    { path: "/users", component: UsersView, meta: { requiresAuth: true } },
    { path: "/create-user", component: UserForm, meta: { requiresAuth: true } },
    { path: "/edit-user/:userId", component: EditUser, props: true, meta: { requiresAuth: true } },


    { path: "/exams", component: ExamListe, meta: { requiresAuth: true } },
    { path: "/create-exam", component: ExamForme, meta: { requiresAuth: true } },
    { path: "/edit-exam/:examId", component: EditExam, props: true, meta: { requiresAuth: true } },


    { path: "/cours", component: CoursView, meta: { requiresAuth: true } },
    { path: "/create-cours", component: CoursForme, meta: { requiresAuth: true } },
    { path: "/edit-course/:courseId", component: EditCours, props: true, meta: { requiresAuth: true } },


    { path: "/questions", component: QuestionView, meta: { requiresAuth: true } },
    { path: "/create-question", component: QuestionForm, meta: { requiresAuth: true } },
    { path: "/edit-question/:questionId", component: EditQuestion, props: true, meta: { requiresAuth: true } },


    { path: "/quizes", component: QuizView, meta: { requiresAuth: true } },
    { path: "/create-quiz", component: QuizForme, meta: { requiresAuth: true } },
    { path: "/edit-quiz/:quizId", component: EditQuiz, props: true, meta: { requiresAuth: true } },
    { path: "/associate-questions/:quizId", component: QuizAssociation, props: true, meta: { requiresAuth: true } },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});


router.beforeEach((to, from, next) => {
    const isAuthenticated = localStorage.getItem("user");

    if (to.meta.requiresAuth && !isAuthenticated) {
        next("/");
    } else {
        next();
    }
});

export default router;
