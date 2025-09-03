package finalmission.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import com.fasterxml.jackson.databind.ObjectMapper;
import finalmission.auth.infrastructure.JwtTokenProvider;
import finalmission.auth.presentation.AuthController;
import finalmission.auth.presentation.AuthorizationExtractor;
import finalmission.auth.service.AuthService;
import finalmission.member.presentation.MemberController;
import finalmission.member.service.MemberService;
import finalmission.reservation.presentation.ReservationController;
import finalmission.reservation.service.ReservationService;
import finalmission.toilet.presentation.ToiletController;
import finalmission.toilet.service.ToiletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(value = {
        MemberController.class,
        AuthController.class,
        ReservationController.class,
        ToiletController.class,
        AuthorizationExtractor.class
})
@ExtendWith(RestDocumentationExtension.class)
public abstract class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    JwtTokenProvider jwtTokenProvider;

    @MockitoBean
    AuthService authService;

    @MockitoBean
    MemberService memberService;

    @MockitoBean
    ReservationService reservationService;

    @MockitoBean
    ToiletService toiletService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint())
                )
                .build();
    }
}
