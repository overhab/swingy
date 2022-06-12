package edu.school21.services;

import edu.school21.dao.ArtifactDao;
import edu.school21.models.Artifact;

public class ArtifactService {

    private final ArtifactDao artifactDao = new ArtifactDao();

    public Artifact findById(long id) {
        return artifactDao.findById(id);
    }

    public Artifact findByTier(int tier) {
        return artifactDao.findByTier(tier);
    }
}
