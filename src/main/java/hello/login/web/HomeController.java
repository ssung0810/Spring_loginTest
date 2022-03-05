package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home(@CookieValue(value = "memberId", required = false) Long memberId,
                       Model model) {

        if (memberId == null) {
            return "home";
        }

        Member findMember = memberRepository.findById(memberId);
        if (findMember == null) {
            return "home";
        }

        model.addAttribute("member", findMember);

        return "loginHome";
    }

    @GetMapping("/")
    public String homeV2(HttpServletRequest request,
                         Model model) {

        Member member = (Member) sessionManager.getSession(request);

        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);

        return "loginHome";
    }
}