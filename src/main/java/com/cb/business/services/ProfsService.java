package com.cb.business.services;

import db.ProfsDAO;
import model.Prof;

import java.util.List;
import java.util.Set;

public class ProfsService {
    private ProfsDAO profsDAO;

    public ProfsService(ProfsDAO profsDAO) {
        this.profsDAO = profsDAO;
    }
    public List<Prof> getProfs() {
        return this.profsDAO.getProfs();
    }

    public Prof getProf(int id) {
        return this.profsDAO.getProf(id);
    }

    public void addProf(Prof prof) {
        this.profsDAO.addProf(prof);
    }

    public void updateProf(Prof prof, int id) {
        this.profsDAO.updateProf(prof, id);
    }

    public void deleteProf(int id) {
        this.profsDAO.deleteProf(id);
    }

    public Set<String> listCompanies() {
        return this.profsDAO.listCompanies();
    }

    public Set<String> listJobTitles() {
        return this.profsDAO.listJobTitles();
    }
}
