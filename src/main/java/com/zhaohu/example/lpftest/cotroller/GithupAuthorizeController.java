package com.zhaohu.example.lpftest.cotroller;

import com.zhaohu.example.lpftest.dto.GitHupAccessTokenDto;
import com.zhaohu.example.lpftest.provider.GithupTokenProvider;
import com.zhaohu.example.lpftest.provider.GithupUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GithupAuthorizeController {

    @Autowired
    GithupTokenProvider githupProvider;

    @Value("${githup.client.id}")
    private String clientId;
    @Value("${githup.client.secret}")
    private String clientSecret;
    @Value("${githup.redirect}")
    private String redirect;

    @GetMapping("/githupcallback")
    public String auth(@RequestParam(name = "code") String code,
                       @RequestParam(name = "state") String state,
                       HttpServletRequest request){
        GitHupAccessTokenDto accessTokenDto = new GitHupAccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri(redirect);
        String githupToken = githupProvider.getGithupToken(accessTokenDto);
        GithupUser githupUser = githupProvider.getGithupUser(githupToken);
        if(githupUser != null){
            request.getSession().setAttribute("user",githupUser);
        }
        return "redirect:/";
    }
}
