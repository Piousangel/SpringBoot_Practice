# SpringBoot_Practice

SpringBoot 공부를 위한 공간입니다.


stream람다식

return store.values.stream()
    .filter(member -> member.getName().equals(name))
    .findAny();

assertThat().isEqualTo();
assertThrows(IllegalStateException.class, () -> memberService.join());

@AfterEach , @BeforeEach clearStore();

//중복처리
public Long join(Member member){
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다")
         });


test문법 1.given 2.when 3. then
