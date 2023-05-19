<template>
  <div id="app">
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
      <router-link to="/user">User</router-link>
    </nav>
    <router-view/>
  </div>
</template>

<script>
// import TheHeader from "@/components/TheHeader.vue";

export default {
	name: "App",
	components: {
		// TheHeader,
	},
};
</script>
