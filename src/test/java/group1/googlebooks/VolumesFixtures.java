package group1.googlebooks;

import group1.googlebooks.model.Volume;
import group1.googlebooks.model.VolumeInfo;
import group1.googlebooks.model.VolumesCollection;

public class VolumesFixtures {
    public static VolumesCollection getCollectionOne() {
        Volume fakeVolume1 = new Volume();
        fakeVolume1.id = "fake-id-1";
        fakeVolume1.kind = "books#volume";
        VolumeInfo fakeVolume1Info = new VolumeInfo();
        fakeVolume1Info.title = "Book 1";
        fakeVolume1Info.authors = new String[]{"Mary Shelley", "Gertrude"};
        fakeVolume1.volumeInfo = fakeVolume1Info;

        Volume fakeVolume2 = new Volume();
        fakeVolume2.id = "fake-id-2";
        fakeVolume2.kind = "books#volume";
        VolumeInfo fakeVolume2Info = new VolumeInfo();
        fakeVolume2Info.title = "Book 2";
        fakeVolume2Info.authors = new String[]{"Ian McEwan", "Rivers Solomon"};
        fakeVolume2.volumeInfo = fakeVolume2Info;

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
