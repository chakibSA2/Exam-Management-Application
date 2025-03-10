<template>
  <form @submit.prevent="login">
    <input v-model="username" placeholder="Nom d'utilisateur">
    <input v-model="password" type="password" placeholder="Mot de passe">
    <button type="submit">Se connecter</button>
  </form>
</template>

<script setup>
import { ref } from 'vue';
import { authStore } from '@/store/auth';
import { useRouter } from 'vue-router';

const router = useRouter();
const username = ref('');
const password = ref('');

const login = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        username: username.value,
        password: password.value
      })
    });

    if(response.ok) {
      const user = await response.json();
      authStore.login(user);
      router.push(user.role === 'ADMIN' ? '/admin' : '/');
    }
  } catch (error) {
    console.error(error);
  }
};
</script>