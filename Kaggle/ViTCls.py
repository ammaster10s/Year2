from torch import nn
from timm.models import convnext

class ViTCls(nn.Module):
    def __init__(self):
        super().__init__()
        self.encoder = convnext.convnext_base(pretrained=True)
        self.cls = nn.Linear(self.encoder.num_features, 7)

    def forward(self, images):
        x = self.encoder.forward_features(images)
        x = self.encoder.forward_head(x, pre_logits=True)
        y = self.cls(x)
        return y
