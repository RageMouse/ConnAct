<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <router-link style="text-decoration: none; color: inherit;" to="/">
          <div id="titleName" class="d-flex display-1">ConnAct</div>
      </router-link>
      <v-tabs centered>
        <v-spacer>
        </v-spacer>
        <v-tab to="/myEvents">My Events</v-tab>
        <v-tab to="/chat">Chats</v-tab>
        <v-tab to="/people">People</v-tab>
        <v-tab to="/event">Events</v-tab>
        <v-tab to="/notification">Notification</v-tab>
        <v-tab to="/profile">Profile</v-tab>
        <v-spacer>
        </v-spacer>
        <v-tab to="/register">Register</v-tab>
        <v-tab to="/login">Login</v-tab>
      </v-tabs>
    </v-app-bar>
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>