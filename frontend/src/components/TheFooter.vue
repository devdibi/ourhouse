<template>
	<footer></footer>
</template>

<script>
export default {
	name: "TheFooter",
	components: {},
	data() {
		return {
			message: "",
		};
	},
	created() {},
	methods: {},
};
</script>

<style scoped>
footer {
	height: 50px;
	width: 100%;
	/* position: fixed; */
	bottom: 0;
	background: rgba(67, 56, 202, 0.8);
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.2);
}
</style>
