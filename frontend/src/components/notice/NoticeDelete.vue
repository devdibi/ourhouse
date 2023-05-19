<template>
    <div>
        삭제중입니다잉
    </div>
</template>

<script>
import {remove} from '@/api/notice.js'
export default {
    name: 'NoticeDelete',
    components: {},
    data() {
        return {
            notice_no:this.$route.params.notice_no
        };
    },
    created() {
        remove(this.notice_no, ({ data }) => {
            if (data === 'success') {
                alert("삭제가 완료되었습니다.")
            } else {
                alert("삭제에 실패했습니다.")
            }
            this.$router.push('/notice')
        })
    },
    methods: {},
};
</script>

<style scoped></style>