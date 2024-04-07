from torch.utils.data import Dataset
import os
import numpy as np
from scipy import fftpack as fft
import cv2
import pandas as pd

classes = ["fall", "jump", "not_fall", "run", "run_sit", "sit_run", "walk"]

overlap = 128
n_rd_history = 256
overlap_count = 0
min_iq = 0
max_iq = 0.05
min_rf = 0
max_rf = 45


class CSVDopplerDatagen(Dataset):
    def __init__(self,
                 csv='/kaggle/input/ultra-wide-band-pose-prediction/annotations.csv',
                 src='/kaggle/input/ultra-wide-band-pose-prediction/train/train'):
        self.csv = pd.read_csv(csv).fillna(-1)
        self.src = src

    def __len__(self):
        return len(self.csv)

    def __getitem__(self, idx):
        row = self.csv.iloc[idx]
        iq = np.load(os.path.join(
            self.src,
            row['id'] + '.npy'
        ))
        if iq.shape[0] > 2560:
            iq = iq[-2560:]
        range_frequency = self.range_frequency([iq])[0]
        image = np.stack((
            iq / max_iq,
            range_frequency / max_rf,
            np.zeros_like(iq),
        ))
        return image, row['class']

    @staticmethod
    def range_frequency(datas):
        Range_frequency_frame = []
        for data in datas:
            jitter = 1e-10
            noise_threshold = -45
            dB = True
            rd_history = np.hanning(data.shape[0])[:, None] * np.array(data)
            # Range-Doppler
            rd = fft.fft(rd_history, axis=0)
            rd = fft.fftshift(rd, axes=0)
            rd = abs(rd)
            if dB:
                rd = 20 * np.log10(rd + jitter)
                rd[rd < noise_threshold] = noise_threshold
            Range_frequency_frame.append(rd)
        return np.stack(Range_frequency_frame)



if __name__ == '__main__':
    dset = CSVDopplerDatagen()
    min_rf = float('inf')
    min_iq = float('inf')
    max_rf = -float('inf')
    max_iq = -float('inf')
    lengths = {2560: 0, 7680: 0}
    for i in range(len(dset)):
        data, _ = dset[i]
        # min_iq = min(min_iq, np.abs(data[0]).min())
        # max_iq = max(max_iq, np.abs(data[0]).max())
        # min_rf = min(min_rf, np.abs(data[1]).min())
        # max_rf = max(max_rf, np.abs(data[1]).max())
        lengths[data.shape[1]] += 1
    x = dset[0]
