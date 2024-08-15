package dev.leo.urlShorter.Links;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


@Service
public class LinkService {

        private LinkRepository linkRepository;

        public LinkService(LinkRepository linkRepository){
            this.linkRepository = linkRepository;
        }

        public String gerarUrlAleatoria(){
            return RandomStringUtils.randomAlphabetic(5, 10);
        }

        public Link encurtaUrl(String urlOriginal){
            Link link = new Link();
            link.setUrlLong(urlOriginal);
            link.setUrlEncurtada(gerarUrlAleatoria());
            link.setUrlCriadaEm(LocalDateTime.now());
            link.setUrlQrCode("indisponivel no momento!");

            return linkRepository.save(link);
        }

        public Link obterUrlOrignnal (String urlEncurtada){
            try{
                return linkRepository.findByUrlOriginal(urlEncurtada);
            }catch (Exception erro){
                throw new RuntimeException("url nao existe");
            }
        }
}
