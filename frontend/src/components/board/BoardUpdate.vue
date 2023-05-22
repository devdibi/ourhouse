<template>
    <div>
        <div class="inner-box">
            <div>
                <span >
                    <input class="title" type="text" placeholder="제목을 입력해주세요." v-model=board.title />
                </span>
            </div>
            <v-divider></v-divider>

            <!-- 본문 영역 -->
            <div class="content-frame" >
                <textarea class="content" placeholder="본문을 작성해주세요" v-model=board.content>
                </textarea>
            </div>
            <v-divider style="margin-bottom:10px"></v-divider>
            <!-- <router-link :to="{name:'boardlist'}"> -->
                <v-btn style="float:right" @click="boardUpdate()">수정</v-btn>
            <!-- </router-link> -->
        </div>
    </div> 
</template>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans&display=swap" rel="stylesheet"/>
<script>
import { detail, update } from '@/api/board.js';

export default {
    name: 'BoardUpdate',
    components: {},
    data() {
        return {
            board: {},
            board_no:this.$route.params.board_no
        };
    },
    created() {
        detail(this.board_no, ({ data }) => {
            console.log(data.board)
            this.board = data.board;
        })
    },
    methods: {
        boardUpdate() {
            update(this.board_no, this.board, ({ data }) => {
                if (data === 'success') {
                    alert("수정 완료")
                } else {
                    alert("수정 실패")
                }
                this.$router.push('/board')
            }),
            (error) => {
                    console.log(error);
            }
        }
    },
};
</script>

<style scoped>
.inner-box{
    margin:auto;
    margin-top:100px;
    box-shadow:0px 4px 4px rgba(0, 0, 0, 0.25);
    min-width:900px;
    max-width:1200px;
    max-height:1080px;
	border-radius:4px;
    border:solid 1px #d9d9d9;
    padding:100px
}
.title { 
    font-size:24px; 
    font-weight: bold;
    width:100%;
    padding:1%
}
.meta{
    font-size:12px; 
    color:#939393;
     margin: 10px 0 5px 10px
}
.content-frame{
    height:500px;
    padding:20px
}
.content{
    width:100%;
    height:100%;
    padding:1%;
    border-radius:4px;
}

.ud{
    text-align:right; 
    font-size:12px;
    margin-right:5px;
    margin-bottom:5px
}

.comment-write{
    padding:1%;
    vertical-align: middle;
    border:solid 1px #f3f3f3;
    margin:10px;
    width:87%;
}

.comment-list{
    padding:10px;
    margin-bottom:10px; 
    margin-left:10px;
    font-size:12px;
    background-color:#f3f3f3
}
</style>