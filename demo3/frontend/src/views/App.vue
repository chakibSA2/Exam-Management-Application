<template>
  <div id="app">
    <header>
      <nav>
        <ul>
          <li><router-link to="/">Accueil</router-link></li>
          <li><router-link to="/exams">Examens</router-link></li>
          <li><router-link to="/users">Utilisateurs</router-link></li>
          <li><router-link to="/cours">Cours</router-link></li>
          <li><router-link to="/questions">Questions</router-link></li>
          <li><router-link to="/quizes">Quiz</router-link></li>
        </ul>
      </nav>
    </header>

    <main>
      <router-view></router-view>
    </main>

    <footer>
      <p>&copy; 2024 - Gestion des Examens</p>
    </footer>
  </div>
</template>

<script setup>
</script>

<style scoped>
/* Styles généraux */
#app {
  font-family: Arial, sans-serif;
  text-align: center;
}

/* Navigation */
header {
  background: #007bff;
  padding: 15px;
}

nav ul {
  list-style: none;
  padding: 0;
  display: flex;
  justify-content: center;
}

nav li {
  margin: 0 15px;
}

nav a {
  color: white;
  text-decoration: none;
  font-size: 16px;
}

nav a:hover {
  text-decoration: underline;
}

/* Contenu principal */
main {
  padding: 20px;
}

/* Footer */
footer {
  margin-top: 20px;
  padding: 10px;
  background: #f4f4f4;
}
</style>
