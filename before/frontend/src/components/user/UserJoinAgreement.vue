<template>
	<div id="box">
		<div>
			<div>
				<div class="title" id="title1">개인정보 수집 이용 동의 <span class="sub">(필수)</span></div>
				<div>
					<b-form-textarea
						id="textarea1"
						class="textarea"
						v-model="text1"
						rows="3"
						max-rows="6"
						readonly
					></b-form-textarea>
				</div>
				<div id="checkbox1">
					<b-form-checkbox v-model="status1" value="동의함" unchecked-value="동의하지 않음">
						{{ status1 }}
					</b-form-checkbox>
				</div>
			</div>
			<div>
				<div class="title" id="title2">마케팅 정보 수신 동의 <span class="sub">(선택)</span></div>
				<div>
					<b-form-textarea
						id="textarea2"
						class="textarea"
						v-model="text2"
						rows="3"
						max-rows="6"
						readonly
					></b-form-textarea>
				</div>
				<div id="checkbox2">
					<b-form-checkbox v-model="status2" value="동의함" unchecked-value="동의하지 않음">
						{{ status2 }}
					</b-form-checkbox>
				</div>
				<div id="checkbox3">개인정보 수집 및 이용, 마케팅 정보 수신(선택)에 모두 동의합니다.</div>
				<div id="checkbox4">
					<b-form-checkbox
						v-model="status3"
						value="동의함"
						unchecked-value="동의하지 않음"
						@change="checkAll()"
					>
						{{ status3 }}
					</b-form-checkbox>
				</div>
				<input type="button" id="previousbtn" value="이전" class="button" @click="previous" />
				<input
					type="button"
					id="nextbtn"
					value="다음"
					class="button"
					@click="next"
					:style="style"
				/>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: "userJoinAgreement",
	components: {},
	data() {
		return {
			status1: "동의하지 않음",
			status2: "동의하지 않음",
			status3: "전체 동의",
			text1: `개인정보보호법에 따라 우리의 집에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.

1. 수집하는 개인정보
이용자는 회원가입을 하지 않아도 정보 검색, 뉴스 보기 등 대부분의 우리의 집 서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 메일, 캘린더, 카페, 블로그 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 우리의 집는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.

회원가입 시점에 우리의 집가 이용자로부터 수집하는 개인정보는 아래와 같습니다.`,
			text2: `우리의 집 서비스 및 제휴 이벤트・혜택 등의 정보를 휴대전화(우리의 집앱 알림 또는 문자), 이메일로 받을 수 있습니다. 일부 서비스(별개의 회원 체계 운영, 우리의 집 가입 후 추가 가입하는 서비스 등)의 경우, 수신에 대해 별도로 안내드리며 동의를 구합니다.`,
			style: {
				//기본: 회색
				background: "rgba(217, 217, 217, 0.6)",
				color: "#939393",
			},
		};
	},
	created() {},
	watch: {
		status1: {
			handler: function () {
				//빈칸 있을 때 - 회색
				if (this.status1 == "동의하지 않음") {
					this.style.background = "rgba(217, 217, 217, 0.6)";
					this.style.color = "#939393";
				} else {
					//동의됨 - 보라
					this.style.background = "#6960d5";
					this.style.color = "#ffffff";
				}
			},
		},
	},
	methods: {
		checkAll() {
			this.status1 = "동의함";
			this.status2 = "동의함";
			console.log(this.status1);
		},
		previous(e) {
			e.preventDefault();
			this.$router.go(-1);
		},
		next(e) {
			e.preventDefault();
			if (this.status1 != "동의함") {
				alert("개인정보 수집 이용 동의가 필요합니다.");
			} else {
				this.$router.push({ path: "joinform" });
			}
		},
	},
};
</script>

<style scoped>
* {
	left: 35px;
	font-family: "Noto Sans";
	font-style: normal;
	text-decoration-line: none;
}

*:link {
	color: #000000;
}

#box {
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	position: absolute;
	width: 800px;
	height: 600px;
	display: inline-block;
	text-align: center;
	background: #ffffff;
	background-blend-mode: color-dodge;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 1px 2px rgba(0, 0, 0, 0.3),
		0px 1px 3px 1px rgba(0, 0, 0, 0.15);
	border-radius: 16px;
}

.title {
	position: absolute;
	font-family: "Noto Sans";
	font-style: normal;
	font-weight: 400;
	font-size: 24px;
	line-height: 33px;
	color: #000000;
}

#title1 {
	top: 32px;
}

#title2 {
	top: 240px;
	margin-top: 20px;
}

.sub {
	font-weight: 400;
	font-size: 18px;
	line-height: 25px;
}

.textarea {
	position: absolute;
	width: 730px;
	height: 100px;
	background: #ffffff;
}

#textarea1 {
	top: 78px;
	margin-bottom: 50px;
}

#textarea2 {
	top: 300px;
}

#checkbox1 {
	position: absolute;
	top: 250px;
	left: 630px;
	font-family: "Noto Sans";
	font-style: normal;
	font-weight: 400;
	font-size: 12px;
	line-height: 16px;
	color: #000000;
}

#checkbox2 {
	position: absolute;
	padding: 0;
	top: 395px;
	left: 630px;
	font-family: "Noto Sans";
	font-style: normal;
	font-weight: 400;
	font-size: 12px;
	line-height: 16px;
	color: #000000;
}

#checkbox3 {
	position: absolute;
	top: 420px;
	left: 410px;
	font-family: "Noto Sans";
	font-style: normal;
	font-weight: 400;
	font-size: 12px;
	line-height: 16px;
	color: #000000;
}

#checkbox4 {
	position: absolute;
	top: 440px;
	left: 630px;
	font-family: "Noto Sans";
	font-style: normal;
	font-weight: 400;
	font-size: 12px;
	line-height: 16px;
	color: #000000;
}

#previousbtn {
	left: 35px;
}
#nextbtn {
	left: 415px;
}

.button {
	position: absolute;
	border: 0;
	width: 350px;
	height: 60px;
	background: #e8e8e8;
	border-radius: 5px;
	top: 490px;

	font-family: "Noto Sans";
	font-style: normal;
	font-size: 16px;
	line-height: 22px;
	text-align: center;
	color: #989898;
}

.button:hover {
	background: #6960d5;
	color: #ffffff;
}
</style>
