package finalmission.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import finalmission.member.dto.response.NicknameResponse;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

public class MemberControllerTest extends ControllerTest {

    @Test
    void 닉네임_추천에_성공한다() throws Exception {
        // given
        NicknameResponse response = new NicknameResponse("추천닉네임1");
        given(memberService.suggestNickname()).willReturn(response);
        // when
        ResultActions result = mockMvc.perform(get("/members/nickname-suggestion"));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("nickname").value("추천닉네임1"))
                .andDo(document("suggest-nickname",
                        responseFields(
                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("추천 닉네임")
                        )
                ));
    }
}
