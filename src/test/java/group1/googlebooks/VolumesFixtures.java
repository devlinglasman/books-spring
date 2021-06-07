package group1.googlebooks;

import group1.googlebooks.model.Volume;
import group1.googlebooks.model.VolumeInfo;
import group1.googlebooks.model.VolumesCollection;

public class VolumesFixtures {
    public static VolumesCollection getCollectionOne() {
        Volume fakeVolume1 = new Volume();
        fakeVolume1.setGoogleId("fake-id-1");
        fakeVolume1.setKind("books#volume");
        VolumeInfo fakeVolume1Info = new VolumeInfo();

        Volume fakeVolume2 = new Volume();
        fakeVolume2.setGoogleId("fake-id-2");
        fakeVolume2.setKind("books#volume");
        VolumeInfo fakeVolume2Info = new VolumeInfo();

        VolumesCollection fakeVolumes = new VolumesCollection();
        fakeVolumes.totalItems = 18;
        fakeVolumes.kind = "books#volumes";
        fakeVolumes.items = new Volume[]{
                fakeVolume1,
                fakeVolume2
        };
        return fakeVolumes;
    }
}
