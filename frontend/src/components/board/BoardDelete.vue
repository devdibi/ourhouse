<template>
    <div>
        삭제중입니다잉
    </div>
</template>

<script>
import {remove} from '@/api/board.js'
export default {
    name: 'BoardDelete',
    components: {},
    data() {
        return {
            board_no:this.$route.params.board_no
        };
    },
    created() {
        remove(this.board_no, ({ data }) => {
            if (data === 'success') {
                alert("삭제가 완료되었습니다.")
            } else {
                alert("삭제에 실패했습니다.")
            }
            this.$router.push('/board')
        })
    },
    methods: {},
};
</script>

<style scoped></style>