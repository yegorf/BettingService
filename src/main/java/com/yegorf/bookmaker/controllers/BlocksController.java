package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.Block;
import com.yegorf.bookmaker.repos.BlockRepo;
import com.yegorf.bookmaker.repos.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController("/blocks")
public class BlocksController {
    private final BlockRepo blockRepo;
    private final UserRepo userRepo;

    public BlocksController(BlockRepo blockRepo, UserRepo userRepo) {
        this.blockRepo = blockRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/addBlock")
    public void addBlock(@RequestParam String date,
                         @RequestParam int term,
                         @RequestParam String reason,
                         @RequestParam int userId) {
        blockRepo.save(new Block(userRepo.findById(userId), date, term, reason));
    }

    @GetMapping("getBlocks")
    public HashSet<Block> getBlocks() {
        return blockRepo.findAll();
    }

    @GetMapping("getUserBlocks")
    public HashSet<Block> getUserBlocks(@RequestParam int userId) {
        return blockRepo.findAllByUser(userRepo.findById(userId));
    }
}
