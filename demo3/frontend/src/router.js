import { createRouter, createWebHistory } from "vue-router";
import UsersView from "@/views/UsersView.vue";
import UserForm from "@/components/UserForm.vue";
import ExamForme from "./components/ExamForme.vue";
import ExamListe from "./components/ExamListe.vue";
import HomeView from "./views/HomeView.vue";
import CoursView from "./views/CoursView.vue";
import CoursForme from "./components/CoursForme.vue";

const routes = [
    { path: "/", component: HomeView },
    { path: "/users", component: UsersView },
    { path: "/create-user", component: UserForm },
    { path: "/exams", component: ExamListe },
    { path: "/create-exam", component: ExamForme },
    { path: "/cours", component: CoursView },
    { path: "/create-cours", component: CoursForme },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
