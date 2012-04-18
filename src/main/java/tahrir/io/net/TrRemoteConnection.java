package tahrir.io.net;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;

import com.google.common.base.Function;

import tahrir.io.net.TrNetworkInterface.TrMessageListener;
import tahrir.io.net.TrNetworkInterface.TrSentReceivedListener;
import tahrir.tools.ByteArraySegment;

public abstract class TrRemoteConnection {
	protected final TrMessageListener listener;
	protected final Function<TrRemoteConnection, Void> connectedCallback;
	protected final TrRemoteAddress remoteAddress;
	protected RSAPublicKey remotePubKey;
	protected final Runnable disconnectedCallback;
	protected final boolean unilateralOutbound;

	protected TrRemoteConnection(final TrRemoteAddress remoteAddress, final RSAPublicKey remotePubKey,
			final TrMessageListener listener, final Function<TrRemoteConnection, Void> connectedCallback,
			final Runnable disconnectedCallback, final boolean unilateralOutbound) {
		this.remoteAddress = remoteAddress;
		// This will be null if this is a unilateral inbound connection
		this.remotePubKey = remotePubKey;
		this.listener = listener;
		this.connectedCallback = connectedCallback;
		this.disconnectedCallback = disconnectedCallback;
		this.unilateralOutbound = unilateralOutbound;
	}

	protected boolean isUnilateralInbound() {
		return remotePubKey == null;
	}

	public TrRemoteAddress getRemoteAddress() {
		return remoteAddress;
	}

	public abstract void send(final ByteArraySegment message, final double priority,
			final TrSentReceivedListener sentListener) throws IOException;

	public abstract boolean isConnected();

	public boolean wasInboundUnilateral() {
		return remotePubKey == null;
	}

	public boolean wasOutboundUnilateral() {
		return unilateralOutbound;
	}

	@Override
	public String toString() {
		return "TrRemoteConnection [remoteAddress=" + remoteAddress
				+ ", remotePubKey=" + remotePubKey + "]";
	}

	public abstract void disconnect();
}
