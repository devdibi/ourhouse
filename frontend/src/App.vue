<template>
	<div>
		<TheHeader />
		<router-view />
	</div>
</template>

<script>
import TheHeader from "@/components/HeaderView.vue";

export default {
	name: "App",
	components: {
		TheHeader,
	},
};
</script>
