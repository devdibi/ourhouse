<template>
  <div class="background">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'userView',
  components: {},
  data() {
    return {
      message: '',
    };
  },
  created() { },
  methods: {},
};
</script>

<style scoped>
.background {
  background: #FFFFFF;
}
</style>