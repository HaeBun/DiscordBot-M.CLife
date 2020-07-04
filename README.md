# DiscordBot-M.CLife
디스코드 봇
디스코드 봇 앰생이 개발일기

- 사실 앰생이 말고도 또 다른 디스코드 봇을 만들고 있다.
- 마인크래프트 서버와 디스코드 봇을 하나로 연결해 디스코드 채널에서 서버 정보 및 물건 구매, 판매가 가능하게 로컬라이징


게임하다보니 디스코드를 사용하게 되었고, 자연스럽게 디스코드 봇에 처음 접함.
처음엔 노래나오는 FredBoat를 보고 이거 재미있겠다 싶어서 구글링.

찾아보니 프로그램을 짜는 것이 마인크래프트 플러그인 개발할 때와 매우 유사함.

유저들의 채팅 기록을 바로바로 확인할 수 있는 Listener
채팅 모양을 좀 더 선명하게 출력해주는 EmbedBuilder
...

그 이외에도 text에 color넣는것도 마인크래프트 플러그인의 ChatColor와 같은 방법으로 간단했다.
( 슬프게도... HTML 처럼 디스코드 봇을 자주 쓰게하려면 디자인이 매우 중요하다. 난 이쪽에선 특출난 재능은 없다. 부지런해져야해 ) 

게다가 커스텀 명령어 만드는 시스템도 플러그인 만들면서 찾아낸 방법으로 작성하니 잘 돌아감.

형식상 DB인 이 시스템으로 각종 재미있는 기능을 넣을 수 있었음.

커맨드 한번 입력시 RAM에서 한번 굴러가고 꺼지는 형식이니
하드에 쌓을 데이터 무조건 받아줘야지

DB를 이용해서 만든 것들은
커스텀 명령어( 앰생이를 만들기 시작한 가장 큰 이유 )
 - 친구들 놀리기 매우 좋은 단축명령어
 - 1월~6월달 동안 명령어 사용 횟수 : 1,647회 ( 아주 뿌듯하다. 서버 한곳에서 1000번이나 넘게 쓴게 기겁할 정도 )
 - 든든한 캐릭터 성격 ( 지랄맞은 성격 )

환율 모의 투자 프로그램 ( 금, 달러, 엔, 위안, 유로 ) - 크롤링 ( 구글에서 정보 습득 )
- 구매 및 판매 기능
- 내 구매 정보
- 서버 랭킹 및 월드 랭킹

개발하다 만 앰생의숲 ( 게임 동물의 숲 모티브 )
- 서버 마을 생성
- 마당 커스터마이징
- 마을 여행 ( 다른 서버들에서 접근 O )
- 무 투자 ( 환율 모의 투자와 비슷 )

앰스타그램 ( 짤 저장소 )
- 명령어로 짤 저장 및 호출
- 명령어로 저장해둔 짤 호출

이거로 다양한 게임도 만들 수 있을 듯 하다.
현재 생각중인 게임은 각자 서버에서 앰생이를 사용할 때마다 레벨을 올려주는 것이다.

내가 만들고 싶은 걸 만들어 보면서 쉬어가고자 했지만, 생태계라 표현해야할까.. 매우 비슷하다.
아프리카TV에서 API개발중이라고 2월달에 소식이 올라왔던데 벌써 6월.
방송하는 지인분의 방송국에 하나 개발해주고 싶구만~