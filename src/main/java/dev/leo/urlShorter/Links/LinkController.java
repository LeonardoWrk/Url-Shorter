package dev.leo.urlShorter.Links;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;


@RestController
public class LinkController {

    private LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/teste")
    public ResponseEntity<LinkResponse> gerarUrlEncurtada(@RequestBody Map<String, String> request){

        String urlOriginal = request.get("urlOriginal");
        Link link = linkService.encurtaUrl(urlOriginal);

        String gerarUrlDeRedirecionamentoDoUsuario = "http://localhost:8080/r/" + link.getUrlEncurtada();

        LinkResponse response = new LinkResponse(
                link.getId(),
                link.getUrlLong(),
                gerarUrlDeRedirecionamentoDoUsuario,
                link.getUrlQrCode(),
                link.getUrlCriadaEm()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/r/{urlEncurtada}")
    public void redeLink(@PathVariable String urlEncurtada, HttpServletResponse response) throws IOException {
        Link link = linkService.obterUrlOrignnal(urlEncurtada);

        if (link !=null){
            response.sendRedirect(link.getUrlLong());
        }else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
