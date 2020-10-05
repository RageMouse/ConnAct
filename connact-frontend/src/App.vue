<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center">
        <router-link style="text-decoration: none; color: inherit;" to="/">
          <div id="titleName" class="d-flex display-1">ConnAct</div>
        </router-link>
      </div>
      <v-spacer></v-spacer>
    </v-app-bar>
    <v-content>
      <router-view />
    </v-content>
  </v-app>
</template>